/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleMapProperty;

/**
 * Classe métier : centralise les valeurs métiers et est sérialisée telle quelle
 * par JSON pour les sauvegardes
 *
 * @author ykonoclast
 */
public class Ninja
{

    private final IntegerProperty m_punch = new SimpleIntegerProperty(0);

    private final IntegerProperty m_kick = new SimpleIntegerProperty(0);

    private final IntegerProperty m_throw = new SimpleIntegerProperty(0);

    private final IntegerProperty m_fate = new SimpleIntegerProperty(0);

    private final IntegerProperty m_endurance = new SimpleIntegerProperty(0);

    private final IntegerProperty m_innerForce = new SimpleIntegerProperty(0);

    private final IntegerProperty m_foeKickDefense = new SimpleIntegerProperty(0);

    private final IntegerProperty m_foePunchDefense = new SimpleIntegerProperty(0);

    private final IntegerProperty m_foeThrowDefense = new SimpleIntegerProperty(0);

    private final IntegerProperty m_foeEndurance = new SimpleIntegerProperty(0);

    private final IntegerProperty m_shurikenLeft = new SimpleIntegerProperty(0);

    private final MapProperty<String, Integer> m_kickTechniques = new SimpleMapProperty<>();//TODO c'est la mise à jour de ce type de map qui va augmenter les valeurs des attaques, pas de mise à jour directe de celles-ci

}
