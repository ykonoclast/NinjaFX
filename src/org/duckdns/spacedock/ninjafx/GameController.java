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
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private AppTab m_currentTab = CARAC;

    @FXML
    private BorderPane fx_borderPane;

    @FXML
    private AnchorPane fx_anchorPane;

    @FXML
    private HBox fx_consolePane;

    private AnchorPane leftPane;

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
	    leftPane = loader.load();//TODO mutualiser ces lignes : passer en paramétre this pour affecter le callback

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
    protected void goLeft(ActionEvent event)
    {
	if (canGoLeft())
	{
	    //TODO : étoffer pour autre pan
	    //on retire les éléments qui sont déjà là
	    fx_borderPane.setCenter(null);
	    fx_borderPane.setBottom(null);
	    fx_borderPane.setCenter(leftPane);
	    m_currentTab = STUFF;
	}
    }

    @FXML
    protected void goRight(ActionEvent event)
    {
	if (canGoRight())
	{
	    //TODO : étoffer pour autre pan
	    //on retire les éléments qui sont déjà là
	    fx_borderPane.setCenter(null);
	    fx_borderPane.setBottom(fx_consolePane);
	    fx_borderPane.setCenter(fx_anchorPane);
	    m_currentTab = CARAC;
	}
    }

    private boolean canGoLeft()//transformer ça en propriétés bindable à l'affichage ou non de la flèche idoine
    {
	return (m_currentTab == CARAC || m_currentTab == NOTES);
    }

    private boolean canGoRight()//transformer ça en propriétés bindable à l'affichage ou non de la flèche idoine
    {
	return (m_currentTab == CARAC || m_currentTab == STUFF);
    }

    public enum AppTab
    {
	STUFF, CARAC, NOTES
	/*{
	    @Override
	    public Player next()
	    {
		return values()[0]; // retour à TALON dans le cas du dernier élément
	    }

	};

	public Player next()
	{
	    // pas besoin de checker si on est au dernier, l'override ci-dessus gère cela
	    return values()[ordinal() + 1];
	}*/
    }
}
