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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * contrôleur du pan notes de l'écran principal
 *
 * @author ykonoclast
 */
public class NotesController
{//TODO nettoyer, virer trucs non-utilisés, renommer etc.

    //affectations des éléments fxml
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

    final ObservableList<String> m_listItems = FXCollections.observableArrayList();//TODO foutre ça dans l'objet Ninja plutôt et binder

    /**
     * constructeur par défaut indispensable pour ne pas provoquer de bugs
     * d'initialisation avec certaines versions de javafx
     */
    public NotesController()
    {
    }

    @FXML
    private void addAction(ActionEvent action)
    {
	m_listItems.add(fx_txtAddItem.getText());
	fx_txtAddItem.clear();
	fx_BtnAdd.setDisable(true);
    }

    @FXML
    private void deleteAction(ActionEvent action)
    {
	int selectedItem = fx_listBoxMain.getSelectionModel().getSelectedIndex();
	m_listItems.remove(selectedItem);
	fx_BtnDelete.setDisable(true);
    }

    /**
     * appelé par le framework
     */
    public void initialize()
    {
	fx_listBoxMain.setItems(m_listItems);

	//par défaut on désactive les boutons : ils seront activés que si le champ texte ou une ligne est focusé
	fx_BtnAdd.setDisable(true);
	fx_BtnDelete.setDisable(true);

	//TODO dans certains cas le focus est mal géré : les boutons restent actifs ou se désactivent au mauvais moment : investiguer
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

	fx_listBoxMain.setCellFactory(new Callback<ListView<String>, ListCell<String>>()
	{
	    @Override
	    public ListCell<String> call(ListView<String> param)
	    {
		return new DeletableCell();
	    }
	});
    }

    //TODO : voir pour passer ça en privé
    static class DeletableCell extends ListCell<String>
    {

	HBox hbox = new HBox();
	Label label = new Label("");
	//Pane pane = new Pane();
	Button button = new Button("Del");

	public DeletableCell()
	{
	    super();

	    hbox.getChildren().addAll(label/*, pane*/, button);
	    button.setOnAction(event -> getListView().getItems().remove(getItem()));
	}

	@Override
	protected void updateItem(String item, boolean empty)
	{
	    super.updateItem(item, empty);

	    if (item != null && !empty)
	    {
		label.setText(item);
		setGraphic(hbox);
	    }
	    else
	    {
		setText(null);
		setGraphic(null);
	    }
	}
    }
}
