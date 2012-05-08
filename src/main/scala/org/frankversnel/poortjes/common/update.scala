package org.frankversnel.poortjes

import org.frankversnel.poortjes.util.DeltaTime

case class Update(deltaTime: DeltaTime, gameObjects: List[GameObject])
