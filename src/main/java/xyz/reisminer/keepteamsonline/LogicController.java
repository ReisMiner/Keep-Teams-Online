package xyz.reisminer.keepteamsonline;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.awt.*;

import java.util.concurrent.ThreadLocalRandom;

public class LogicController {

    public CheckBox mouseWiggleCbx;
    public CheckBox randomInputCbx;
    public TextField randomLettersInput;
    public Button toggleActiveBtn;

    private boolean isActive = false;
    private Robot r;

    @FXML
    protected void toggleActiveBtnClick() throws AWTException {
        isActive = !isActive;

        //change UI elements
        if (isActive) {
            r = new Robot();
            toggleActiveBtn.setText("Stop! [return]");
            randomInputCbx.setDisable(true);
            mouseWiggleCbx.setDisable(true);
        } else {
            toggleActiveBtn.setText("Start!");
            randomInputCbx.setDisable(false);
            mouseWiggleCbx.setDisable(false);
            r = null;
        }

        if (mouseWiggleCbx.isSelected()) {
            randomLettersInput.requestFocus();
            Thread mouse = new Thread(() -> {
                while (isActive) {
                    int y = ThreadLocalRandom.current().nextInt(0, 1080 + 1);
                    int x = ThreadLocalRandom.current().nextInt(0, 1920 + 1);
                    r.mouseMove(x,y);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            mouse.setDaemon(true); //thread ends aswell when program closes
            mouse.start();
        }

        //Random Letter Typing
        if (randomInputCbx.isSelected()) {
            randomLettersInput.requestFocus();
            Thread letters = new Thread(() -> {
                while (isActive) {
                    int x = ThreadLocalRandom.current().nextInt(65, 90 + 1);
                    r.keyPress(x);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            letters.setDaemon(true); //thread ends aswell when program closes
            letters.start();

        }
    }
}