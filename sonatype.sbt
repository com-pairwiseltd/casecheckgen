sonatypeProfileName := "com.pairwiseltd"
publishMavenStyle := true
licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitHubHosting("ergunbaris", "casecheckgen", "barisergun75@gmail.com"))
homepage := Some(url("https://github.com/com-pairwiseltd/casecheckgen"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/com-pairwiseltd/casecheckgen"),
    "scm:git@github.com:com-pairwiseltd/casecheckgen.git"
  )
)
developers := List(
  Developer(id="bergun", name="Baris Ergun", email="baris@pairwiseltd.com", url=url("www.pairwiseltd.com"))
)
