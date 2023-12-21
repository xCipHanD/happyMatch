package asia.sustech.happymatch.Particles;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.effect.Glow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;

import static asia.sustech.happymatch.GameController.GameController.getImageViewByGridPaneCoordinates;
import static asia.sustech.happymatch.GameController.GameController.getRelativeBoundsToWindow;

public class ExplosionEffect {
    private static final int NUM_PARTICLES = 25;
    private static final double EXPLOSION_RADIUS = 30;
    private static final double PARTICLE_RADIUS = 1.5;
    private static final double FADE_DURATION = 0.5;

    private static final Random random = new Random();

    public static void explode(AnchorPane root, double x, double y) {
        for (int i = 0; i < NUM_PARTICLES; i++) {
            double angle = 2 * Math.PI * random.nextDouble();
            double radius = random.nextDouble() * EXPLOSION_RADIUS;
            double startX = x + radius * Math.cos(angle);
            double startY = y + radius * Math.sin(angle);

            Circle particle = createParticle(startX, startY);
            root.getChildren().add(particle);

            double endX = x + random.nextDouble() * 2 - 1;
            double endY = y + random.nextDouble() * 2 - 1;

            animateParticle(particle, endX, endY);
        }
    }

    private static Circle createParticle(double x, double y) {
        Circle particle = new Circle(x, y, PARTICLE_RADIUS, Color.WHITE);
        particle.setEffect(new Glow(1.0));

        return particle;
    }

    private static void animateParticle(Circle particle, double endX, double endY) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(particle.centerXProperty(), endX)),
                new KeyFrame(Duration.ZERO, new KeyValue(particle.centerYProperty(), endY)),
                new KeyFrame(Duration.ZERO, new KeyValue(particle.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(FADE_DURATION), new KeyValue(particle.centerXProperty(),
                        particle.getCenterX())),
                new KeyFrame(Duration.seconds(FADE_DURATION), new KeyValue(particle.centerYProperty(),
                        particle.getCenterY())),
                new KeyFrame(Duration.seconds(FADE_DURATION), new KeyValue(particle.opacityProperty(), 0.0))
        );

        timeline.setOnFinished(event -> particle.setVisible(false));
        timeline.play();
    }

    //跟据gridPane和xy坐标，进行爆炸效果
    public static void playParticleEffect(GridPane gridPane, int row, int column) {
        // 获取指定格子的中心点坐标
        double centerX =
                getRelativeBoundsToWindow(getImageViewByGridPaneCoordinates(gridPane, row, column)).getCenterX();
        double centerY =
                getRelativeBoundsToWindow(getImageViewByGridPaneCoordinates(gridPane, row, column)).getCenterY();
        // 调用粒子效果方法，传入中心点坐标
        Platform.runLater(() -> explode((AnchorPane) gridPane.getParent().getParent(), centerX, centerY));
    }
}
