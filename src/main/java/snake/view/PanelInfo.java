package snake.view;

import snake.controller.Controller;
import snake.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelInfo extends JPanel
{
    private final Game game;

    private final JLabel lblScore;
    private final JLabel lblBestScore;

    public PanelInfo(Controller ctrl, Game game)
    {
        // Parameters
        this.game = game;

        this.setBackground(Color.WHITE);

        // Key bindings
        InputMap inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("pressed SPACE"), "SPACE");
        ActionMap actionMap = this.getActionMap();
        actionMap.put("SPACE", new GlobalAction(ctrl::start));

        // Components
        this.lblScore = new JLabel("Score : " + this.game.getScore());
        this.lblBestScore = new JLabel("Best score : " + this.game.getBestScore());

        // Layout
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        this.add(new JLabel("Press SPACE to start / restart"));
        this.add(this.lblScore);
        this.add(this.lblBestScore);
    }

    public void refreshInfo()
    {
        this.lblScore.setText("Score : " + this.game.getScore());
        this.lblBestScore.setText("Best score : " + this.game.getBestScore());
    }


    private static class GlobalAction extends AbstractAction
    {
        private final Runnable action;

        public GlobalAction(Runnable action)
        {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            this.action.run();
        }
    }
}
