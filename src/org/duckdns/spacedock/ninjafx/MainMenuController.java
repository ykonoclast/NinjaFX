/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * contrôle le menu principal
 *
 * @author ykonoclast
 */
public class MainMenuController implements Initializable, IFxmlController
{

    /**
     * pointeur de retour sur l'application principale
     */
    private IHigherLevelCallback m_mainApp;

    /**
     * constructeur par défaut indispensable pour ne pas provoquer de bugs
     * d'initialisation avec certaines versions de javafx
     *
     * un constructeur peut aussi êttre utile pour gérer les exceptions levées
     * dans l'initialisation des membres (notamment des final)
     */
    public MainMenuController()
    {
    }

    /**
     * appelé automatiquement par le framework, attention la Scene n'est pas
     * encore disponible pour l'instant, ne pas y faire appel ici où dans les
     * méthodes appelées
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
	//rien pour l'instant
    }

    /**
     *
     * @param p_App
     */
    @Override
    public void setHigherLevelCallback(IHigherLevelCallback p_App)
    {
	m_mainApp = p_App;
    }

    /**
     * lance l'écran principal de jeu pour une nouvelle partie
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void newGame(ActionEvent event) throws IOException
    {
	m_mainApp.displayScene("GameScreen.fxml");
    }
}
