package tv.lazygoat.spark.common
import scala.reflect.runtime.{universe => ru}
import org.apache.spark.sql.{DataFrame, Dataset, Column, SparkSession}
import org.apache.spark.sql.{functions => F};



object DatasetMapping {
  
  def toTypedDs[T](spark: SparkSession, df: DataFrame)(
    implicit ttag: ru.TypeTag[T]): DataFrame = {
    import spark.implicits._

    val sym = ru.typeOf[T].typeSymbol
    val root = sym.annotations.head
    val mainCtor = sym.info.decls
      .find(d => d.isMethod && d.asMethod.isPrimaryConstructor)
      .get
    val params = mainCtor.typeSignature.paramLists.head

    // The name of the fields.
    val columnNames = params.flatMap(p => p.annotations)
      .map(ann => ann.tree.children.tail(0).children(1).toString)
      // This is a workaround.. not sure how to get the value of the attribute
      // unquoted.
      .map(s => s.stripPrefix("\"").stripSuffix("\""))

    // The name of the column.
    val fieldNames = params
      .filter(p => p.annotations.nonEmpty)
      .map(_.name.toString)

    val fieldToColumn = columnNames.zip(fieldNames)
    val columns: List[Column] = fieldToColumn
      .map(p => F.col(p._1).as(p._2))

    return df.select(columns:_*)
  }
}
