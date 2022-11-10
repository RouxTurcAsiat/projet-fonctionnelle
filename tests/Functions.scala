package tests

import project.Operators.LogicalOp, LogicalOp.*
import project.Functions.semtab, project.Tableau
import project.Functions.isSatisfiable, project.Functions.isContradiction, project.Functions.isTautology

object Semtab extends TestFramework:
    def testProjectStatement: Result[Tableau] =
        val formula: List[LogicalOp] = List(
          Or(Atom("p"), Atom("q")),
          Atom("r"),
          Not(Atom("q")),
        )
        val formula2: List[LogicalOp] = List(
          Nand(Atom("a"), Atom("b"))
        )

        val output = List(
            List(false, false, true),
            List(false, true, true),
            List(true, false, false),
            List(true, true, false),
            List(true, false, true),
            List(true, true, true),
            List(true, false, false),
            List(true, true, false)
        )

        val output2 = List(
          List(true),
          List(true),
          List(true),
          List(false)
        )

        //this.assertEqual(semtab(formula), output)

        this.assertEqual(semtab(formula2), output2)


object IsSatisfiable extends TestFramework:
	def test: Result[Boolean] =
		val formula: List[LogicalOp] = List(
          Or(Atom("p"), Atom("q")),
          Atom("r"),
          Not(Atom("q")),
        )

		val output = true

		this.assertEqual(isSatisfiable(formula), output)

object IsTautology extends TestFramework:
	def test: Result[Boolean] =
		val formula: List[LogicalOp] = List(
          Or(Atom("p"), Atom("q")),
          Atom("r"),
          Not(Atom("q")),
        )

		val f: List[LogicalOp] = List(
			IfThen(And(IfThen(Atom("p"), Atom("q")),
			Atom("p")), Atom("q"))
		)

		val output = false
		val o = true

		this.assertEqual(isTautology(formula), output)
		this.assertEqual(isTautology(f), o)

object IsContradiction extends TestFramework:
	def test: Result[Boolean] =
		val formula: List[LogicalOp] = List(
          Or(Atom("p"), Atom("q")),
          Atom("r"),
          Not(Atom("q")),
        )

		val f: List[LogicalOp] = List(
			And(Atom("p"), Not(Atom("p")))
		)

		val output = false
		val o = true

		this.assertEqual(isContradiction(formula), output)

		this.assertEqual(isContradiction(f), o)