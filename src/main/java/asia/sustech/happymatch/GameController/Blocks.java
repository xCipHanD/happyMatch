package asia.sustech.happymatch.GameController;

import javafx.scene.image.Image;

import java.util.Objects;

public enum Blocks {
    ICE, B1, B2, B3, B4, B5, B6, B11, B12, B13, B14, B15, B16, B9, B10;

    public Image getImage() {
        String url = switch (this) {
            case B1 -> "1";
            case B2 -> "2";
            case B3 -> "3";
            case B4 -> "4";
            case B5 -> "5";
            case B6 -> "6";
            case B11 -> "1C";
            case B12 -> "2C";
            case B13 -> "3C";
            case B14 -> "4C";
            case B15 -> "5C";
            case B16 -> "6C";
            case B9 -> "iceC";
            case B10 -> "0C";
            default -> "ice";
        };
        return new Image(Objects.requireNonNull(Blocks.class.getResourceAsStream("/Game/blocks/" + url + ".png")));
    }
}
