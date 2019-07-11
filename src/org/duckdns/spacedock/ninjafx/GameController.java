/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static org.duckdns.spacedock.ninjafx.GameController.AppTab.CARAC;
import static org.duckdns.spacedock.ninjafx.GameController.AppTab.NOTES;
import static org.duckdns.spacedock.ninjafx.GameController.AppTab.STUFF;

/**
 *
 * @author ykonoclast
 */
public class GameController implements IFxmlController
{

    private IMainAppCallback m_mainApp;
    private AppTab m_currentTab = CARAC;//TODO binder ça au texte du label en haut dans la barre et implémenter le tostring
    //TODO binder aussi ça à l'affichage ou non des boutons gauche ou droite

    @FXML
    private BorderPane fx_borderPane;

    @FXML
    private AnchorPane fx_anchorPane;

    @FXML
    private HBox fx_consolePane;

    @FXML
    private ToolBar fx_navBar;

    @FXML
    private Button fx_btnGoLeft;

    @FXML
    private Button fx_btnGoRight;

    private AnchorPane leftPane;

    private VBox rightPane;

    /**
     * appelé automatiquement par le framework, attention la Scene n'est pas
     * encore disponible pour l'instant, ne pas y faire appel ici où dans les
     * méthodes appelées.
     *
     * Attention à ne pas confondre ça avec l'ancienne façon de faire (interface
     * initializable) ici on utilise l'injection de dépendances
     *
     */
    @FXML
    public void initialize()
    {
	try
	{
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("StuffPane.fxml"));//TODO voir si on peut pas éviter ce getclass
	    leftPane = loader.load();//TODO voir pour mutualiser ces lignes avec l'appli principale
	    loader = new FXMLLoader(getClass().getResource("NotesPane.fxml"));//TODO voir si on peut pas éviter ce getclass
	    rightPane = loader.load();//TODO voir pour mutualiser ces lignes avec l'appli principale
	}
	catch (IOException e)
	{
	    //TODO traiter l'exception
	    System.err.println(e.getMessage());
	}
    }

    @Override
    public void setMainAppCallback(IMainAppCallback p_App)
    {
	m_mainApp = p_App;
    }

    @FXML
    private void goLeft(ActionEvent event)
    {
	switch (m_currentTab)
	{
	    case CARAC:
		fx_borderPane.setCenter(null);
		fx_borderPane.setBottom(null);
		fx_borderPane.setCenter(leftPane);
		m_currentTab = STUFF;
		break;
	    case NOTES:
		fx_borderPane.setBottom(fx_consolePane);
		fx_borderPane.setCenter(fx_anchorPane);
		m_currentTab = CARAC;
		break;
	}
    }

    @FXML
    private void goRight(ActionEvent event)
    {
	switch (m_currentTab)
	{
	    case CARAC:
		fx_borderPane.setCenter(rightPane);
		fx_borderPane.setBottom(null);
		m_currentTab = NOTES;

		break;
	    case STUFF:
		fx_borderPane.setBottom(fx_consolePane);
		fx_borderPane.setCenter(fx_anchorPane);
		m_currentTab = CARAC;
		break;
	}
    }

    public enum AppTab
    {
	STUFF, CARAC, NOTES
    }
}
