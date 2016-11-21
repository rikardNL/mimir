package se.di.ntl.changes.common

import doobie.util.composite.Composite

object DoobieSupport {
  implicit class SqlIdeFixer(sc: StringContext) extends doobie.syntax.string.SqlInterpolator(sc) {
    def dsql[A: Composite](args: A) = this.sql.applyProduct(args)
  }
}