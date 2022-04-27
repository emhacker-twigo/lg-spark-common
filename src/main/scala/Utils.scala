package tv.lazygoat.spark.common
import org.apache.spark.sql.{Dataset}
import java.util.Calendar


object Utils {
  val calendar = Calendar.getInstance()

  /**
   * @description Binds the given value into the given function,
   * effectively reducing the arity of its parameter.
   **/
  def bind[T, U, V](bindVal: T, f: (T, Dataset[U]) => Dataset[V]):
  (Dataset[U] => Dataset[V]) = (ds: Dataset[U]) => f(bindVal, ds)

  def bind2[T, U, V, W](val1: T, val2: U, f: (T, U, Dataset[V]) => Dataset[W]):
  (Dataset[V] => Dataset[W]) = (ds: Dataset[V]) => f(val1, val2, ds)

  def getCurrentDateDir(): String = {
    val dt = Array(
      Calendar.YEAR,
      Calendar.MONTH,
      Calendar.DAY_OF_MONTH)
      .map(p => calendar.get(p))
      .mkString("-")
    s"dt=${dt}"
  }

  def getCurrentTimeDir(): String = {
    val tm = calendar.get(Calendar.HOUR_OF_DAY)
    s"tm=${tm}"
  }

}
