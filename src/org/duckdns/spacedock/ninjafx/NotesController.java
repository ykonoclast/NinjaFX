/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

/**
 * contrôleur de la vue customisée de gestion de notes
 *
 * @author ykonoclast
 */
public class NotesController implements INotesController
{

    //affectations des éléments fxml
    @FXML
    private ListView<String> fx_listBoxMain;

    @FXML
    private Label fx_aTitleLbl;

    /**
     * la liste des notes inscrites
     */
    final ObservableList<String> m_listItems = FXCollections.observableArrayList();//TODO binder à l'objet ninja

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
	//on ouvre la petite fenêtre servant à ajouter une note
	TextInputDialog td = new TextInputDialog();//le comportement est modal par défaut dans JavaFX
	td.initStyle(StageStyle.UNDECORATED);//pour ne pas avoir de barre de fenêtre
	td.setHeaderText(null);//pour ne pas avoir de header
	td.setGraphic(null);//limite encore plus la taille de cette petite fenêtre
	Optional<String> winStatus = td.showAndWait();//tout est bloqué pendant l'affichage et derrière on récupère le bouton sur lequel l'utilisateur a appuyé
	if (winStatus.isPresent())
	{//le isPresent est faux si on a cliqué sur cancel ou fermé la fenêtre par tout autre moyen
	    m_listItems.add(td.getEditor().getText());//on récupère le contenu du champ texte de la petite pop-up
	}
    }

    /**
     * appelé par le framework
     */
    public void initialize()
    {
	fx_listBoxMain.setItems(m_listItems);//on fait le lien entre la ListView et la liste des String

	fx_listBoxMain.setCellFactory(new DeletableCellFactory());//on place une CellFactory customisée pour produire des DeletableCell
    }

    @Override
    public void setTitle(String p_title)
    {
	fx_aTitleLbl.setText(p_title);
    }
}
