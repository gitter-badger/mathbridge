package simianquant.mathbridge

object NormalDistribution extends NormalDistributionInterface {
  private final val sqrt2 = math.sqrt(2)

  override final def cdf(x: Double): Double = (1.0 - MathJSFacade.erf(-x / sqrt2)) / 2.0

  // taken from https://gist.github.com/kmpm/1211922/6b7fcd0155b23c3dc71e6f4969f2c48785371292
  @SuppressWarnings(Array("org.wartremover.warts.Equals", "org.wartremover.warts.Throw"))
  override final def quantile(p: Double): Double = {
    if (p < 0 || p > 1) {
      throw new Exception("The probality p must be bigger than 0 and smaller than 1")
    }

    if (p == 0) {
      Double.NegativeInfinity;
    }
    if (p == 1) {
      Double.PositiveInfinity;
    }

    var q = 0.0
    var r = 0.0
    var res = 0.0

    q = p - 0.5;

    if (math.abs(q) <= .425) {
      r = .180625 - q * q;
      res =
        q * (((((((r * 2509.0809287301226727 +
          33430.575583588128105) * r + 67265.770927008700853) * r +
          45921.953931549871457) * r + 13731.693765509461125) * r +
          1971.5909503065514427) * r + 133.14166789178437745) * r +
          3.387132872796366608) / (((((((r * 5226.495278852854561 +
          28729.085735721942674) * r + 39307.89580009271061) * r +
          21213.794301586595867) * r + 5394.1960214247511077) * r +
          687.1870074920579083) * r + 42.313330701600911252) * r + 1);
    } else {
      if (q > 0)
        r = 1 - p;
      else
        r = p;

      r = math.sqrt(-math.log(r));

      if (r <= 5) {
        r += -1.6;
        res = (((((((r * 7.7454501427834140764e-4 +
          .0227238449892691845833) * r + .24178072517745061177) *
          r + 1.27045825245236838258) * r +
          3.64784832476320460504) * r + 5.7694972214606914055) *
          r + 4.6303378461565452959) * r +
          1.42343711074968357734) / (((((((r *
          1.05075007164441684324e-9 + 5.475938084995344946e-4) *
          r + .0151986665636164571966) * r +
          .14810397642748007459) * r + .68976733498510000455) *
          r + 1.6763848301838038494) * r +
          2.05319162663775882187) * r + 1);
      } else {
        r += -5;
        res = (((((((r * 2.01033439929228813265e-7 +
          2.71155556874348757815e-5) * r +
          .0012426609473880784386) * r + .026532189526576123093) *
          r + .29656057182850489123) * r +
          1.7848265399172913358) * r + 5.4637849111641143699) *
          r + 6.6579046435011037772) / (((((((r *
          2.04426310338993978564e-15 + 1.4215117583164458887e-7) *
          r + 1.8463183175100546818e-5) * r +
          7.868691311456132591e-4) * r + .0148753612908506148525)
          * r + .13692988092273580531) * r +
          .59983220655588793769) * r + 1);
      }

      if (q < 0.0) {
        res = -res;
      }
    }

    res
  }

  // def main(args: Array[String]): Unit = {
  //   var x = -5.0
  //   while (x < 5.0) {
  //     println(s"x: $x, res: ${quantile(cdf(x)) - x}")
  //     x += 0.2
  //   }
  // }

}
