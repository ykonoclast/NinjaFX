/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * CellFactory produisant des DeletableCell pour les ListView et gérant les
 * erreurs d'instanciations
 *
 * @author ykonoclast
 */
public class DeletableCellFactory implements Callback<ListView<String>, ListCell<String>>
{

    @Override
    public ListCell<String> call(ListView<String> param)
    {
	ListCell<String> result = null;
	try
	{
	    result = new DeletableCell();
	}
	catch (IOException e)
	{
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.initStyle(StageStyle.UNDECORATED);
	    alert.setHeaderText(null);
	    alert.setContentText("Erreur d'accès aux fichiers de configuration");

	    alert.show();
	}
	return result;
    }
}
