package test

import org.openjdk.jmh.annotations.Benchmark

import scala.io.Source

class TestBenchmark {
  import TestBenchmark._

  def implFold(x: Option[Char]) = x.fold(false)(_.isLower)

  def implPatMat(x: Option[Char]) = x match {
    case Some(c) => c.isLower
    case None    => false
  }

  def implPatMat2(x: Option[Char]) = x match {
    case None    => false
    case Some(c) => c.isLower
  }

  def implIsAsInstanceOf(x: Option[Char]) =
    if (x.isInstanceOf[Some[Char]]) x.asInstanceOf[Some[Char]].get.isLower else false

  @Benchmark def foldSome(): Boolean = implFold(someChar)
  @Benchmark def foldNone(): Boolean = implFold(noneChar)

  @Benchmark def patMatSome(): Boolean = implPatMat(someChar)
  @Benchmark def patMatNone(): Boolean = implPatMat(noneChar)

  @Benchmark def patMatSome2(): Boolean = implPatMat2(someChar)
  @Benchmark def patMatNone2(): Boolean = implPatMat2(noneChar)

  @Benchmark def isAsInstanceOfSome(): Boolean = implIsAsInstanceOf(someChar)
  @Benchmark def isAsInstanceOfNone(): Boolean = implIsAsInstanceOf(noneChar)
}

object TestBenchmark {
  val someChar: Option[Char] = Some('c')
  val noneChar: Option[Char] = None
}
