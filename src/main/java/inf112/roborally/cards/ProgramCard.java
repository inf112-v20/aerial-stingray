package inf112.roborally.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.*;

public class ProgramCard {

    /**
     * Each card's texture is 140x260 px
     */
    private final Dimension dimension = new Dimension(140, 260);
    private int priority;
    private CardType type;
    /**
     * ImageButton corresponding to cardtype.
     */
    private ImageButton imageButton;

    public ProgramCard(CardType type, int priority) {
        this.type = type;
        this.priority = priority;

        imageButton = new ImageButton(
                getTextureFromType(type, false),
                getTextureFromType(type, true),
                getTextureFromType(type, true)
        );
        imageButton.setSize(dimension.width, dimension.height);
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    /**
     * Given a CardType, returns corresponding texture.
     *
     * @param type The type of the card
     * @return Corresponding texture of cardtype
     */
    private TextureRegionDrawable getTextureFromType(CardType type, boolean pressed) {
        String state = "_notPressed.png";
        if (pressed)
            state = "_pressed.png";

        TextureRegionDrawable texture;
        switch (type) {
            case MOVE1:
                texture = new TextureRegionDrawable(new Texture(Gdx.files.internal("cards/move1" + state)));
                break;

            case MOVE2:
                texture = new TextureRegionDrawable(new Texture(Gdx.files.internal("cards/move2" + state)));
                break;

            case MOVE3:
                texture = new TextureRegionDrawable(new Texture(Gdx.files.internal("cards/move3" + state)));
                break;

            case BACKUP:
                texture = new TextureRegionDrawable(new Texture(Gdx.files.internal("cards/backup" + state)));
                break;

            case TURN_U:
                texture = new TextureRegionDrawable(new Texture(Gdx.files.internal("cards/uTurn" + state)));
                break;

            case TURN_LEFT:
                texture = new TextureRegionDrawable(new Texture(Gdx.files.internal("cards/leftRotate" + state)));
                break;

            case TURN_RIGHT:
                texture = new TextureRegionDrawable(new Texture(Gdx.files.internal("cards/rightRotate" + state)));
                break;

            default:
                texture = null;
        }

        return texture;
    }

    public CardType getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }
}
