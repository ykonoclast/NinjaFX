/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * véritable classe principale : gère la fenêtre et effectue le démarrage de
 * l'appli
 *
 * @author ykonoclast
 */
public class NinjaFX extends Application implements IMainAppCallback
{//NOTE : on n'utilise pas de subscene car l'on n'a pas besoin de séparer les éléments propres à la scène comme la caméra ou les antialiasing

    private Stage m_mainStage;

    /**
     * appelée par le framework dans un nouveau thread : ce qui sera fait dans
     * le main ne sera donc pas sur le même thread (pour ne pas bloquer le GUI
     * par des accès par exemple)
     *
     * @param stage
     * @throws IOException
     */
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
    public void displayScene(String p_fxmlFileName) throws IOException
    {
	FXMLLoader loader = new FXMLLoader(getClass().getResource(p_fxmlFileName));//TODO voir si on peut pas éviter ce getclass
	Parent root = loader.load();

	IFxmlController controller = (IFxmlController) loader.getController();
	controller.setMainAppCallback(this);

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

    /**
     * ce main est a priori dans un thread différent de ce qui est lancé par le
     * start (qui est appelé par le launch)
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
	launch(args);
    }
}
