/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.io.IOException;
import java.util.function.Consumer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author ykonoclast
 */
public class NinjaFX extends Application implements IMainAppCallback
{

    private Stage m_mainStage;

    private IFxmlController m_currentController;

    @Override
    public void start(Stage stage) throws IOException
    {
	m_mainStage = stage;
	m_mainStage.setTitle("NinjaFX");//TODO Strings i18n

	displayScene("MainMenu.fxml");
	m_mainStage.show();
	m_mainStage.setFullScreen(true);

    }

    @Override
    public void displayScene(String p_xmlFileName) throws IOException
    {
	Parent root = getParentFromFxml(p_xmlFileName);

	//scene.getStylesheets().add(Form.class.getResource("Form.css").toExternalForm());//incantation pour le css TODO pourquoi le class.getressource?
	Scene currentScene = m_mainStage.getScene();

	if (currentScene != null)
	{//une scène existe déjà : on remplace donc uniquement son contenu pour ne pas redimensionner ou faire clignoter la fenêtre principale en remplaçant carrément la scène
	    currentScene.setRoot(root);
	}
	else
	{//on est à l'initialisation : aucune scène n'a encore été créée
	    m_mainStage.setScene(new Scene(root));//on ne précise pas la taille de la Scene créée : on utilise un stage fullscreen
	}
    }

    /*
    @Override
    public void displayMenu() throws IOException
    {
	displayScene("MainMenu.fxml");
    }

    @Override
    public void displayGameScreen() throws IOException
    {
	displayScene("GameScreen.fxml");
    }
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
	launch(args);
    }

    @Override
    public Parent getParentFromFxml(String p_fxmlFileName) throws IOException
    {
	FXMLLoader loader = new FXMLLoader(getClass().getResource(p_fxmlFileName));//TODO voir si on peut pas éviter ce getclass
	Parent result = loader.load();

	m_currentController = (IFxmlController) loader.getController();
	m_currentController.setMainAppCallback(this);

	return result;
    }
}
