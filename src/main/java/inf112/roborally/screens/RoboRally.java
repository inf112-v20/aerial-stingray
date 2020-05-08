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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.roborally.Main;
import inf112.roborally.cards.CardType;
import inf112.roborally.cards.Deck;
import inf112.roborally.cards.ProgramCard;
import inf112.roborally.entities.Color;
import inf112.roborally.entities.Player;
import inf112.roborally.events.EventUtil;
import inf112.roborally.ui.Board;
import inf112.roborally.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Handles logic of game & controlling players.
 */
public class RoboRally implements Screen {

    /**
     * Constants
     */
    public final static int MAX_VISIBLE_CARDS = 9;
    public final static int MAX_SELECTED_CARDS = 5;

    /**
     * Rendering
     */
    public SpriteBatch batch;
    private BitmapFont font;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
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
     * All players in the game.
     */
    private Player[] players;

    /**
     * Holds the references to the ImageButtons, representing the cards on the screen.
     */
    private ImageButton[] cardButtons;

    /**
     * Order of (Player, Card) to be executed
     */
    private LinkedList<Pair<Player, ProgramCard>> executeOrder;

    /**
     * Buttons
     */
    private TextButton finishedBtn;
    private TextButton undoBtn;
    private TextButton executeBtn;

    public RoboRally(int numPlayers) {
        Gdx.graphics.setContinuousRendering(false);

        setupGameComponents();
        setupPlayers(numPlayers);
        setupRendering();
        setupUI();
        setupInput();

        dealCardsToAll();  // First phase
    }

    /**
     * Phase 1 - deal cards to all players.
     */
    private void dealCardsToAll() {
        System.out.println("[  PHASE 1  ] Dealing out cards to all");

        refillHumanCards();
        giveBotsCards();

        setupCardButtons();
        updateCardGraphics();
    }

    /**
     * Gives new cards to human (not selecting).
     */
    private void refillHumanCards() {
        // Deleting prev. cards.
        getHumanPlayer().setSelectedCards(new ProgramCard[MAX_SELECTED_CARDS]);

        ProgramCard[] visibleCards = deck.take(MAX_VISIBLE_CARDS);
        getHumanPlayer().setVisibleCards(visibleCards);
    }

    /**
     * Gives bots cards.
     */
    private void giveBotsCards() {
        for (Player player : players) {
            if (player.isBot())
                player.setSelectedCards(deck.take(5));
        }
    }

    /**
     * Phase 2 - called when player cards is selected.
     */
    private void selectCards() {
        System.out.println("[  PHASE 2  ] Cards selected!");
        printHumanSelectedCards();

        finishedBtn.remove();
        undoBtn.remove();
        promptPowerDown();  // Next phase - Not functional atm.
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
            if (!player.isBot()) continue;

            if (random.nextInt(21) == 0)
                player.setPowerDown(true);
            else
                player.setPowerDown(false);
        }

        // Human dialog for power down
        Skin skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
        Dialog dialog = new Dialog("Power Down", skin) {
            @Override
            protected void result(Object object) {
                try {
                    String str = (String) object;
                    if (str.equals("Power down")) {
                        System.out.println("[  THIS_ROBOT  ] Power down");
                        getHumanPlayer().setPowerDown(true);
                        executeRobotCards();  // Next phase
                    } else if (str.equals("Don't power down")) {
                        System.out.println("[  THIS_ROBOT  ] Don't power down");
                        getHumanPlayer().setPowerDown(false);
                        executeRobotCards();  // Next phase
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
    private void executeRobotCards() {
        System.out.println("[  PHASE 4  ] Ready to execute cards!");

        sortPlayers();
        setupExecuteBtn();  // Phase 4
    }

    /**
     * Sorts players based on card priority for each round.
     */
    private void sortPlayers() {
        executeOrder = new LinkedList<>();  // Delete prev. content

        for (int i = 0; i < MAX_SELECTED_CARDS; i++) {

            // Sort players for each card
            Player[] playersCopy = players;
            final int ii = i;
            Arrays.sort(playersCopy, Comparator.comparingInt(a -> a.getSelectedCards()[ii].getPriority()));
            for (Player p : playersCopy) {
                executeOrder.add(new Pair<>(p, p.getSelectedCards()[i]));
            }
        }
    }

    /**
     * Phase 4 - clicking this button will execute next card.
     */
    private void setupExecuteBtn() {
        Skin skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
        executeBtn = new TextButton("Execute next card!", skin);
        executeBtn.setSize(200, 80);
        executeBtn.setPosition((float) Main.WIDTH - 350, 200);
        executeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (executeOrder.size() <= 0) {
                    executeBtn.remove();
                    cleanUp();  // Next phase
                    return;
                }
                Pair<Player, ProgramCard> nextPair = executeOrder.pop();
                Player player = nextPair.getFirst();
                ProgramCard card = nextPair.getSnd();

                String entity = !player.isBot() ? "[  HUMAN  ]" : "[  BOT " + player.getID() + "  ]";
                if (player.isDead()) {
                    System.out.println(entity + " The robot is dead, and will not execute cards");
                } else {
                    System.out.println(entity + " Executes card " + card.getType() + " with priority " + card.getPriority());
                    executeCard(player, card);
                }

                if (executeOrder.size() % 4 == 0) {
                    EventUtil.handleEvent(board, players);
                    //drawStatus();
                }

                clearScreen();
                actAndRender(Gdx.graphics.getDeltaTime());
            }
        });
        stage.addActor(executeBtn);
    }

    /**
     * Executes a card
     *
     * @param selectedCard The card to execute
     */
    public void executeCard(Player player, ProgramCard selectedCard) {
        Vector2 oldPos = player.getPos();
        setCellToNull(oldPos);

        player.executeCard(board, selectedCard, players);
    }

// --Commented out by Inspection START (07.05.2020, 23:34):
//    /**
//     * Shoots laser from all players.
//     */
//    public void shootLasers() {
//        for (Player player : this.players) {
//            player.shootLaser(board);
//        }
//    }
// --Commented out by Inspection STOP (07.05.2020, 23:34)

    /**
     * Phase 5 - ending round and cleaning up board.
     */
    private void cleanUp() {
        System.out.println("[  PHASE 5  ] Ending round and cleaning up board.");
        for (Player player : players) {
            if (player.isDead()) {
                player.respawn();
                System.out.println("Respawning " + player.getID());
            }
        }

        recycleCards();

        setupUndoBtn();
        setupFinishedButton();

        // Removes all prev. cards
        removeCardButtons();
        dealCardsToAll();  // Starting back at phase 1
    }

    private void removeCardButtons() {
        for (ImageButton button : cardButtons)
            button.remove();
    }

    private Player getHumanPlayer() {
        for (Player player : players) {
            if (!player.isBot()) return player;
        }

        throw new NullPointerException("Human player could not be found!");
    }

    private void setupGameComponents() {
        deck = new Deck();
        board = new Board();
        cardButtons = new ImageButton[MAX_VISIBLE_CARDS];

        executeOrder = new LinkedList<>();
    }

    private void setupPlayers(int numPlayers) {
        players = new Player[numPlayers];
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.PINK};
        Vector2[] startPos = {new Vector2(6, 1), new Vector2(9, 1), new Vector2(13, 1), new Vector2(16, 1)};

        for (int i = 0; i < numPlayers; i++) {
            if (i == 0)
                players[i] = new Player(startPos[i], colors[i], i, false);  // Human
            else
                players[i] = new Player(startPos[i], colors[i], i, true);
        }
    }

    private void setupRendering() {
        int DECK_WINDOW_SIZE = 280;
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

        setupFinishedButton();
        // setupCardButtons();
        setupUndoBtn();

        ScreenManager.getInstance().setScreen(this);
    }

    /**
     * Adds necessary buttons to the screen.
     * As of now, only one button.
     */
    private void setupFinishedButton() {
        Skin skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
        finishedBtn = new TextButton("Finished!", skin);
        finishedBtn.setSize(200, 80);
        finishedBtn.setPosition((float) Main.WIDTH / 2 - (finishedBtn.getWidth() / 2), 200);
        finishedBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // If player can successfully lock in cards, begin round
                if (humanHasEnoughCards())
                    selectCards();
            }
        });
        stage.addActor(finishedBtn);
    }

    private void setupUndoBtn() {
        Skin skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
        undoBtn = new TextButton("Undo", skin);
        undoBtn.setSize(200, 80);
        undoBtn.setPosition((float) Main.WIDTH - 550, 200);
        undoBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getHumanPlayer().removeCards();
                printHumanSelectedCards();
                uncheckAllCards();
            }
        });
        stage.addActor(undoBtn);
    }

    /**
     * Adds the programming cards (9 of them) to the screen.
     * Init cards.
     */
    private void setupCardButtons() {
        int startX = 21;
        int margin = 155;
        for (int i = 0; i < MAX_VISIBLE_CARDS; i++) {
            int visibleCardIndex = i;

            ImageButton btn = new ImageButton(null, null, null);
            btn.setPosition(i * margin + startX, 0);
            btn.setSize(140, 200);
            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (getHumanPlayer().selectedCards() == MAX_SELECTED_CARDS && btn.isChecked()) {
                        btn.setChecked(false);
                        System.err.println("Can't choose more cards (MAX=5).");
                        return;
                    }

                    if (btn.isChecked())
                        getHumanPlayer().addCard(visibleCardIndex);

                    printHumanSelectedCards();
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
     * Checks if player has selected enough cards.
     */
    public boolean humanHasEnoughCards() {
        if (getHumanPlayer().selectedCards() == MAX_SELECTED_CARDS)
            return true;
        else if (getHumanPlayer().selectedCards() > MAX_SELECTED_CARDS)
            System.err.println("Too many cards to lock in.");
        else
            System.err.println("Too few cards to lock in.");

        return false;
    }

    /**
     * Recycles cards that has been executed back to deck.
     * Also empties the cards currently chosen.
     */
    public void recycleCards() {
        for (Player player : players) {
            deck.recycleAll(player.getSelectedCards());
            player.setSelectedCards(new ProgramCard[MAX_SELECTED_CARDS]);
        }
    }

    /**
     * Unchecking all ImageButtons representing the cards.
     * This only needs to be done for this player, as only that player has GUI.
     */
    public void uncheckAllCards() {
        for (ImageButton btn : cardButtons) {
            if (btn.isChecked())
                btn.setChecked(false);
        }
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
     * Updating the ImageButtons to correspond to the new cards.
     * <p>
     * This only needs to be done for this player, as only that player has GUI.
     */
    public void updateCardGraphics() {
        int startX = 27;
        int margin = 5;
        for (int i = 0; i < MAX_VISIBLE_CARDS; i++) {
            Skin skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
            int priority = getHumanPlayer().getVisibleCards()[i].getPriority();

            Label priorityPoints = new Label(Integer.toString(priority), skin);
            priorityPoints.setSize(10, 20);
            priorityPoints.setPosition(margin + startX, 165);

            ImageButton.ImageButtonStyle oldImageButtonStyle = cardButtons[i].getStyle();
            oldImageButtonStyle.imageUp = getHumanPlayer().getVisibleCards()[i].getImageUp();
            oldImageButtonStyle.imageChecked = getHumanPlayer().getVisibleCards()[i].getImageDown();
            oldImageButtonStyle.imageDown = getHumanPlayer().getVisibleCards()[i].getImageDown();

            cardButtons[i].setStyle(oldImageButtonStyle);
            cardButtons[i].addActor(priorityPoints);
            if (cardButtons[i].isChecked()) {
                cardButtons[i].removeActor(priorityPoints);
            }
        }
    }

    /**
     * Prints all the cards the user currently has chosen.
     */
    public void printHumanSelectedCards() {
        if (getHumanPlayer().getSelectedCards().length == 0) return;

        System.out.print("[  HUMAN  ] ");
        for (int i = 0; i < getHumanPlayer().getSelectedCards().length; i++) {
            if (getHumanPlayer().getSelectedCards()[i] == null)
                System.out.print("(" + i + ", NONE) ");
            else {
                CardType cardType = getHumanPlayer().getSelectedCards()[i].getType();
                System.out.print("(" + i + ", " + cardType + ") ");
            }
        }
        System.out.println();
    }

    @Override
    public void render(float v) {
        clearScreen();

        actPlayers();
        actAndRender(Gdx.graphics.getDeltaTime());

        status();
    }

    private void status() {
        batch.begin();

        int yMargin = 10;
        for (Player player : players) {
            font.draw(batch, player.status(), 10, Gdx.graphics.getHeight() - yMargin);
            yMargin += 30;
        }
        batch.end();
    }

    /**
     * Clears the screen with a set background color.
     */
    public void clearScreen() {
        // Removing player sprites
        for (int y = 0; y < Gdx.graphics.getHeight(); y++)
            for (int x = 0; x < Gdx.graphics.getWidth(); x++) {
                board.getPlayerLayer().setCell(x, y, null);
            }


        Gdx.gl.glClearColor(178 / 255f, 148 / 255f, 119 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Updates all players' position and if any won.
     */
    public void actPlayers() {
        for (Player player : this.players) {
            board.getPlayerLayer().setCell((int) player.getPos().x, (int) player.getPos().y, player.getPlayerIcon());
            board.getPlayerLayer().getCell((int) player.getPos().x, (int) player.getPos().y).getTile().setId(player.getID());

            player.won();
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

    @Override
    public void show() {
        //Intentionally empty body
    }

    @Override
    public void resize(int width, int height) {
        //Intentionally empty body
    }

    @Override
    public void pause() {
        //Intentionally empty body
    }

    @Override
    public void resume() {
        //Intentionally empty body
    }

    @Override
    public void hide() {
        //Intentionally empty body
    }
}
