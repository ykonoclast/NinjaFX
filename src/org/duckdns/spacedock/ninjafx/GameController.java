/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author ykonoclast
 */
public class GameController implements Initializable, IFxmlController
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
}
