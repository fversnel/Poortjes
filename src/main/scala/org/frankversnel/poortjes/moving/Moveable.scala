package org.frankversnel.poortjes.moving

import org.frankversnel.poortjes._

trait Moveable extends Component {
    var transform: Transform

    def move {
        transform.rotate(0.01f)
        transform.translate(0, 0.01f)
    }
}
