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
 * pas besoin d'un constructeur : on ne définit pas de membre pouvant lever des
 * exceptions sur initialisation ou autre truc devant se faire dès la création
 * de l'objet
 *
 * @author ykonoclast
 */
public class MainMenuController implements Initializable, IFxmlController
{

    private INinjAppCallback m_mainApp;

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

    @Override
    public void setApp(INinjAppCallback p_App)
    {
	m_mainApp = p_App;
    }

    @FXML
    protected void newGame(ActionEvent event) throws IOException
    {
	m_mainApp.displayGameScreen();
    }
}
