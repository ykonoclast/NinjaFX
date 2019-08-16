/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static org.duckdns.spacedock.ninjafx.GameController.AppTab.NOTES;
import static org.duckdns.spacedock.ninjafx.GameController.AppTab.STUFF;
import static org.duckdns.spacedock.ninjafx.GameController.AppTab.STATS;

/**
 * Contrôle l'écran principal de jeu de l'application : celui-ci est en
 * plusieurs pans qui sont construits et affectés ici.
 *
 * @author ykonoclast
 */
public class GameController implements IFxmlController
{

    /**
     * application principale, principalement pour retourner au menu principal
     */
    private IHigherLevelCallback m_mainApp;

    /**
     * pan dans lequel on se situe actuellement
     */
    private AppTab m_currentTab = STATS;

    /**
     * Property du texte à afficher en haut de l'écran dans la barrre de
     * navigation
     *
     * pas besoin d'une vraie String comme socle : la property peut suffire,
     * notamment dans le cas des objets non métiers.
     */
    private final StringProperty m_currentTabStringProperty = new SimpleStringProperty(m_currentTab.toString());

    /**
     * property indiquant si l'on est dans le pan extrême gauche : en ce cas on
     * ne peut pas aller plus loin
     *
     * pas besoin d'un vrai booléen comme socle : la property peut suffire,
     * notamment dans le cas des objets non métiers.
     */
    private final BooleanProperty m_isFullLeft = new SimpleBooleanProperty(false);

    /**
     * property indiquant si l'on est dans le pan extrême droit : en ce cas on
     * ne peut aller plus loin
     */
    private final BooleanProperty m_isFullRight = new SimpleBooleanProperty(false);

    //membres provenant du fichier fxml
    @FXML
    private BorderPane fx_borderPane;

    @FXML
    private AnchorPane fx_anchorPane;

    @FXML
    private HBox fx_consolePane;

    @FXML
    private Button fx_btnGoLeft;

    @FXML
    private Button fx_btnGoRight;

    private AnchorPane leftPane;

    private VBox rightPane;

    @FXML
    private Label fx_navLabel;

    /**
     * constructeur par défaut indispensable pour ne pas provoquer de bugs
     * d'initialisation avec certaines versions de javafx
     *
     * un constructeur peut aussi êttre utile pour gérer les exceptions levées
     * dans l'initialisation des membres (notamment des final)
     */
    public GameController()
    {
    }

    /**
     * appelé automatiquement par le framework, attention la Scene n'est pas
     * encore disponible pour l'instant, ne pas y faire appel ici où dans les
     * méthodes appelées.
     *
     * Attention à ne pas confondre ça avec l'ancienne façon de faire (interface
     * initializable) ici il n'y a pas de paramétres : c'est le chargement du
     * fxml qui déclenche l'appel.
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
	    //TODO traiter l'exception via un toast ou carrément un dialogue indiquant que l'on va devoir fermer l'appli si c'est trop grave
	    System.err.println(e.getMessage());
	}

	//bindings des affichage et contenu éléments de navigation aux valeurs de contrôle maintenue dans ce contrôleur
	fx_navLabel.textProperty().bind(m_currentTabStringProperty);
	fx_btnGoLeft.visibleProperty().bind(m_isFullLeft.not());
	fx_btnGoRight.visibleProperty().bind(m_isFullRight.not());
    }

    /**
     * permet de garder trace de l'appli appelante pour retourner au menu plus
     * tard
     *
     * @param p_App
     */
    @Override
    public void setHigherLevelCallback(IHigherLevelCallback p_App)
    {
	m_mainApp = p_App;
    }

    /**
     * passe un pan à gauche dans l'écran : il faut enlever ce qui remplit le
     * borderpane (ce n'est pas détruit : on conserve des pointeurs sur ces
     * objets) et mettre à la place les éléments graphiques qui doivent s'y
     * trouver
     *
     * @param event
     */
    @FXML
    private void goLeft(ActionEvent event)
    {
	switch (m_currentTab)
	{
	    case STATS://on va dans le stuff
		fx_borderPane.setCenter(null);
		fx_borderPane.setBottom(null);
		fx_borderPane.setCenter(leftPane);
		m_currentTab = STUFF;
		m_isFullLeft.set(true);
		break;
	    case NOTES://on va dans les stats
		fx_borderPane.setBottom(fx_consolePane);
		fx_borderPane.setCenter(fx_anchorPane);
		m_currentTab = STATS;
		m_isFullRight.set(false);
		break;
	}
	m_currentTabStringProperty.set(m_currentTab.toString());
    }

    /**
     * passe un pan à droite dans l'écran : il faut enlever ce qui remplit le
     * borderpane (ce n'est pas détruit : on conserve des pointeurs sur ces
     * objets) et mettre à la place les éléments graphiques qui doivent s'y
     * trouver
     *
     * @param event
     */
    @FXML
    private void goRight(ActionEvent event)
    {
	switch (m_currentTab)
	{
	    case STATS://on va dans les notes
		fx_borderPane.setCenter(rightPane);
		fx_borderPane.setBottom(null);
		m_currentTab = NOTES;
		m_isFullRight.set(true);

		break;
	    case STUFF:
		fx_borderPane.setBottom(fx_consolePane);
		fx_borderPane.setCenter(fx_anchorPane);
		m_currentTab = STATS;
		m_isFullLeft.set(false);
		break;
	}
	m_currentTabStringProperty.set(m_currentTab.toString());
    }

    /**
     * un pan de l'écran
     */
    public enum AppTab
    {
	STUFF, STATS, NOTES
    }
}
