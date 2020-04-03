package inf112.roborally.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.roborally.Main;
import inf112.roborally.cards.Deck;
import inf112.roborally.cards.ProgramCard;
import inf112.roborally.entities.Color;
import inf112.roborally.entities.Player;
import inf112.roborally.ui.Board;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Handles logic of game & controlling players.
 */
public class RoboRally implements Screen {

    /**
     * Const.
     */
    private final int DECK_WINDOW_SIZE = 280;
    private final int MAX_SELECTED_CARDS = 5;
    public final static int NUM_CARDS_SERVED = 9;

    /**
     * Rendering
     */
    public SpriteBatch batch;
    private BitmapFont font;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    /**
     * Components are contained by stage.
     */
    private Stage stage;

    /**
     * Board to be played on.
     */
    private Board board;

    /**
     * Deck to choose cards from.
     */
    private Deck deck;

    /**
     * All players on board.
     */
    private ArrayList<Player> players;

    /**
     * Storage for the cards displayed to player, and for the card held by player.
     */
    private LinkedList<ProgramCard> cardsChosen;
    private ImageButton[] cardButtons;


    public RoboRally(int numPlayers) {
        setupGameComponents();
        setupPlayers(numPlayers);
        setupRendering();
        setupUI();
        setupInput();

        dealCardsToAll();
    }

    /**
     * Phase 1 - deal cards to all players.
     */
    private void dealCardsToAll() {
        // Deal initial cards - phase 1
        System.out.println("[  PHASE 1  ] Dealing out cards to all");
        dealCards();
        refreshImageButtons();
    }

    private Player getThisPlayer() {
        return players.get(0);
    }

    /**
     * Phase 3 - ask if each player want to power down.
     * Starts to ask this player.
     * All the other players choose on a pseudo-random basis.
     */
    private void promptPowerDown() {
        // Other players deciding if power down - 5 % chance
        Random random = new Random();
        for (Player player : players) {
            if (random.nextInt(21) == 0)
                player.setPowerDown(true);
            else
                player.setPowerDown(false);
        }

        // This player deciding if power down
        Skin skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
        Dialog dialog = new Dialog("Power Down", skin) {
            @Override
            protected void result(Object object) {
                try {
                    String str = (String) object;
                    if (str.equals("Power down")) {
                        System.out.println("[  THIS_ROBOT  ] Power down");
                        getThisPlayer().setPowerDown(true);
                        executeAllPlayerCards();
                    } else if (str.equals("Don't power down")) {
                        System.out.println("[  THIS_ROBOT  ] Don't power down");
                        getThisPlayer().setPowerDown(false);
                        executeAllPlayerCards();
                    }
                } catch (ClassCastException cce) {
                    cce.printStackTrace();
                }
            }
        };
        dialog.text("Do you want to power down your robot next round?");
        dialog.button("Yes", "Power down");
        dialog.button("No", "Don't power down");
        System.out.println("[  PHASE 3  ] Prompting for power down");

        dialog.show(stage);
    }

    /**
     * Phase 4 - executes all player cards, in order, based on card power.
     */
    private void executeAllPlayerCards() {
        System.out.println("[  PHASE 4  ] Ready to execute cards!");
    }

    private void setupGameComponents() {
        deck = new Deck();
        board = new Board();
        cardButtons = new ImageButton[NUM_CARDS_SERVED];
    }

    private void setupPlayers(int numPlayers) {
        players = new ArrayList<>();
        cardsChosen = new LinkedList<>();
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.PINK};
        Vector2[] startPos = {new Vector2(6,1), new Vector2(9,1), new Vector2(13,1), new Vector2(16,1)};

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(startPos[i], colors[i], i));
        }

        //getThisPlayer() = getThisPlayer();
    }

    private void setupRendering() {
        resize(Main.WIDTH, Main.HEIGHT + DECK_WINDOW_SIZE);

        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.y -= DECK_WINDOW_SIZE;
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(board.getMap());
        mapRenderer.setView(camera);
    }

    private void setupUI() {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        setupButtons();
        setupCardButtons();

        ScreenManager.getInstance().setScreen(this);
    }

    /**
     * Adds necessary buttons to the screen.
     * As of now, only one button.
     */
    private void setupButtons() {
        Skin skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
        TextButton submitCards = new TextButton("Lock in cards!", skin);
        submitCards.setSize(200, 80);
        submitCards.setPosition((float) Main.WIDTH / 2 - (submitCards.getWidth() / 2), 200);
        submitCards.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // If player can successfully lock in cards, begin round
                if (lockInCards()) {
                    selectCardsToBots();
                    promptPowerDown();  // Not functional atm.
                }
            }
        });
        stage.addActor(submitCards);
    }

    /**
     * Adds the programming cards (9 of them) to the screen.
     * Init cards.
     */
    private void setupCardButtons() {
        int startX = 21;
        int margin = 155;
        for (int i = 0; i < NUM_CARDS_SERVED; i++) {
            int index_copy = i;

            ImageButton btn = new ImageButton(null, null, null);
            btn.setPosition(i * margin + startX, 0);
            btn.setSize(140, 200);
            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!btn.isChecked())
                        removeClickedCardFromStack(index_copy);

                    if (cardsChosen.size() == MAX_SELECTED_CARDS) {
                        btn.setChecked(false);
                        System.err.println("Can't choose more cards (MAX=5).");
                    } else if (btn.isChecked())
                        addClickedCardToStack(index_copy);
                }
            });
            cardButtons[i] = btn;

            stage.addActor(btn);
        }
    }

    /**
     * Sets up input processor.
     */
    private void setupInput() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Deals as many cards as possible to each player.
     */
    private void dealCards() {
        for (Player player : players) {
            fillInNewCards(player.getCards());
        }
    }

    /**
     * Locks in the selected cards and furthers the process
     * of executing them.
     */
    public boolean lockInCards() {
        if (cardsChosen.size() == MAX_SELECTED_CARDS) {
            System.out.println("[  PHASE 2  ] Locking in cards!");
            printChosenCards();
            executeCards(cardsChosen);
            return true;
        } else if (cardsChosen.size() > MAX_SELECTED_CARDS)
            System.err.println("Too many cards to lock in.");
        else
            System.err.println("Too few cards to lock in.");

        return false;
    }

    /**
     * Executes a stack of cards
     *
     * @param cardStack The cards to execute
     */
    public void executeCards(Queue<ProgramCard> cardStack) {
        while (!cardStack.isEmpty()) {
            ProgramCard currentCard = cardStack.poll();

            Vector2 oldPos = getThisPlayer().getPos();
            setCellToNull(oldPos);

            getThisPlayer().executeCard(board, currentCard, players);
        }

        recycleAndDisplayNewCards();
    }

    /**
     * Sets a cell in the player layer, at a certain position, to null.
     *
     * @param pos The position of the cell
     */
    public void setCellToNull(Vector2 pos) {
        board.getPlayerLayer().setCell((int) pos.x, (int) pos.y, null);
    }

    /**
     * Wrapper method for:
     * - recycling selected cards
     * - deselecting all cards
     * - getting new cards from the deck
     * - updating ImageButtons of the cards
     */
    private void recycleAndDisplayNewCards() {
        recycleCards();
        deselectAllCards();
        dealCards();
        refreshImageButtons();
    }

    /**
     * Recycles cards that has been executed back to deck.
     * Also empties the cards currently chosen.
     */
    public void recycleCards() {
        for (Player player : players) {
            for (int i = 0; i < NUM_CARDS_SERVED; i++) {
                if (player.getCards()[i].getImageButton().isChecked()) {
                    deck.recycle(player.getCards()[i].copy());
                }
            }
            cardsChosen = new LinkedList<>();
        }
    }

    /**
     * Deselecting all ImageButtons representing the cards.
     * This only needs to be done for this player, as only that player has GUI.
     */
    public void deselectAllCards() {
        for (int i = 0; i < NUM_CARDS_SERVED; i++) {
            if (cardButtons[i].isChecked()) {
                cardButtons[i].setChecked(false);
                getThisPlayer().getCards()[i] = null;
            }
        }
    }

    /**
     * Fetching new cards from the deck to replace the 5 executed.
     *
     * @param currentCards The current cards the player has
     */
    public void fillInNewCards(ProgramCard[] currentCards) {
        for (int i = 0; i < NUM_CARDS_SERVED; i++) {
            if (currentCards[i] == null) {
                currentCards[i] = deck.pop();
            }
        }
    }

    /**
     * Updating the ImageButtons to correspond to the new cards.
     * This only needs to be done for this player, as only that player has GUI.
     */
    public void refreshImageButtons() {
        for (int i = 0; i < NUM_CARDS_SERVED; i++) {
            ImageButton.ImageButtonStyle oldImageButtonStyle = cardButtons[i].getStyle();
            oldImageButtonStyle.imageUp = getThisPlayer().getCards()[i].getImageUp();
            oldImageButtonStyle.imageChecked = getThisPlayer().getCards()[i].getImageDown();
            oldImageButtonStyle.imageDown = getThisPlayer().getCards()[i].getImageDown();

            cardButtons[i].setStyle(oldImageButtonStyle);
        }
    }

    /**
     * Adding a card to the queue.
     * This only needs to be done for this player, as only that player has GUI.
     *
     * @param index Index of the card in the array of cards to chose.
     */
    public void addClickedCardToStack(int index) {
        if (cardsChosen.size() >= MAX_SELECTED_CARDS) {
            System.err.println("[  THIS_ROBOT  ] Can't add any more cards to stack.");
            return;
        }

        cardsChosen.add(getThisPlayer().getCards()[index]);
        printChosenCards();
    }

    private void selectCardsToBots() {
        for (Player player : players) {
            if (player.equals(getThisPlayer())) continue;

            // Selects the five first cards for bots
            for (int i = 0; i < MAX_SELECTED_CARDS; i++)
                addCardToStack(player, i);
        }
    }

    public void addCardToStack(Player player, int index) {
        if (cardsChosen.size() >= MAX_SELECTED_CARDS) {
            System.err.println("Can't add any more cards to stack.");
            return;
        }

        System.out.println("[  ROBOT " + (players.indexOf(player)) + "  ] Adding " + player.getCards()[index].getType() + " to selected hand!");

        player.getChosenCards().add(player.getCards()[index]);
        printChosenCards();
    }

    /**
     * Removing a card from the queue.
     * This only needs to be done for this player, as only that player has GUI.
     *
     * @param index Index of the card in the array of cards to chose.
     */
    public void removeClickedCardFromStack(int index) {
        if (cardsChosen.size() == 0) {
            System.err.println("[  THIS_ROBOT  ] Can't remove any more cards from stack.");
            return;
        }

        System.out.println("[  THIS_ROBOT  ] Removing " + getThisPlayer().getCards()[index].getType() + " from stack.");

        cardsChosen.remove(getThisPlayer().getCards()[index]);
        printChosenCards();
    }

    /**
     * Prints all the cards the user currently has chosen.
     */
    public void printChosenCards() {
        if (cardsChosen.size() == 0) return;

        System.out.print("[  THIS_ROBOT  ] : [");
        for (int i = 0; i < cardsChosen.size(); i++) {
            if (i != cardsChosen.size() - 1)
                System.out.print("(" + i + ", " + cardsChosen.get(i).getType() + "), ");
            else
                System.out.println("(" + i + ", " + cardsChosen.get(i).getType() + ")]");
        }
    }

    @Override
    public void render(float v) {
        clearScreen();

        actPlayers();
        actAndRender(Gdx.graphics.getDeltaTime());
    }

    /**
     * Clears the screen with a set background color.
     */
    public void clearScreen() {
        Gdx.gl.glClearColor(178 / 255f, 148 / 255f, 119 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Updates all players' position and if any won.
     */
    public void actPlayers() {
        int i = 0;
        for (Player player : this.players) {
            board.getPlayerLayer().setCell((int) player.getPos().x, (int) player.getPos().y, player.getPlayerIcon());
            board.getPlayerLayer().getCell((int) player.getPos().x, (int) player.getPos().y).getTile().setId(i);
            i++;

            player.checkIfWon();
        }
    }

    /**
     * Wrapper method for acting and rendering the stage, map and camera.
     *
     * @param v The delta-time used (usually Gdx.graphics.getDeltaTime())
     */
    public void actAndRender(float v) {
        camera.update();
        mapRenderer.render();

        stage.act(v);
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    /**
     * Non-finished method implemented from Screen.
     */
    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        Gdx.graphics.setWindowedMode(width, height);
    }

    /**
     * Non-finished method implemented from Screen.
     */
    @Override
    public void pause() {
    }

    /**
     * Non-finished method implemented from Screen.
     */
    @Override
    public void resume() {
    }

    /**
     * Non-finished method implemented from Screen.
     */
    @Override
    public void hide() {
    }
}
