package org.frankversnel.poortjes.input.gamepad

sealed abstract class Button(val index: Int)
case object BackButton extends Button(4)
