/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * contrôleur du pan équipement de l'écran principal
 *
 * @author ykonoclast
 */
public class StuffController
{//TODO nettoyer, virer trucs non-utilisés, renommer etc.

    @FXML
    private Button fx_BtnAdd;

    @FXML
    private Button fx_BtnDelete;

    @FXML
    private HBox fx_HBox4Btns;

    @FXML
    private Label fx_LblAddText;

    @FXML
    private ListView<String> fx_listBoxMain;//TODO voir pour cellfactory et pour icones dans la liste afin de supprimer les entrées

    @FXML
    private Label fx_TitleLbl;

    @FXML
    private VBox fx_VBoxMain;

    @FXML
    private TextField fx_txtAddItem;

    final ObservableList<String> listItems = FXCollections.observableArrayList();//TODO binder avec l'objet Ninja

    /**
     * constructeur par défaut indispensable pour ne pas provoquer de bugs
     * d'initialisation avec certaines versions de javafx
     */
    public StuffController()
    {
    }

    /**
     * ajoute un élément à la liste d'équipement
     *
     * @param action
     */
    @FXML
    private void addAction(ActionEvent action)
    {
	listItems.add(fx_txtAddItem.getText());
	fx_txtAddItem.clear();
	fx_BtnAdd.setDisable(true);
    }

    /**
     * retire un élément à la liste d'équipement
     *
     * @param action
     */
    @FXML
    private void deleteAction(ActionEvent action)
    {
	int selectedItem = fx_listBoxMain.getSelectionModel().getSelectedIndex();
	listItems.remove(selectedItem);
	fx_BtnDelete.setDisable(true);
    }

    /**
     * appelé par le framework
     */
    public void initialize()
    {
	fx_listBoxMain.setItems(listItems);

	//par défaut on désactive les boutons : ils seront activés que si le champ texte ou une ligne est focusé
	fx_BtnAdd.setDisable(true);
	fx_BtnDelete.setDisable(true);

	//si on focus le champ texte : le bouton ajout est activé
	fx_txtAddItem.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
	{
	    if (fx_txtAddItem.isFocused())
	    {
		fx_BtnAdd.setDisable(false);
	    }
	});

	//si on focus une ligne de la liste, le bouton suppression est activé
	fx_listBoxMain.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
	{
	    if (fx_listBoxMain.isFocused())
	    {
		fx_BtnDelete.setDisable(false);
	    }
	});
    }
}
