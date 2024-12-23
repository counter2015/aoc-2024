import scala.io.Source
import scala.util.Using

object day1 {

  private def getData: (List[Long], List[Long]) = {
    Using.resource(Source.fromResource("data/data1.txt")) { source =>
      val input = source.getLines().map(_.split(" {3}")).map(arr => (arr(0).toLong, arr(1).toLong))
      val (first, second) = input.toList.unzip
      (first, second)
    }
  }

  private val data = getData

  @main
  def solutionP1(): Long = {
    val (first, second) = data
    val res = first.sorted.zip(second.sorted).map((a, b) => Math.abs(a - b)).sum
    println(res)
    res
  }

  @main
  def solutionP2(): Long = {
    val (first, second) = data
    val keys = first.toSet
    val wordCount = second.groupMapReduce(identity)(_ => 1)(_ + _).withDefault(_ => 0)
    val res = keys.map(key => wordCount(key) * key).sum
    println(res)
    res
  }
}
