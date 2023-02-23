addCommandAlias("c", "compile")
addCommandAlias("ca", "Test / compile")
addCommandAlias("t", "test")
addCommandAlias("r", "run")
addCommandAlias(
  "styleCheck",
  "scalafmtSbtCheck; scalafmtCheckAll",
)
addCommandAlias(
  "styleFix",
  "scalafmtSbt; scalafmtAll",
)
addCommandAlias(
  "up2date",
  "reload plugins; dependencyUpdates; reload return; dependencyUpdates",
)

onLoadMessage +=
  s"""|
      |╭─────────────────────────────────╮
      |│     List of defined aliases     │
      |├─────────────┬───────────────────┤
      |│ c           │ compile           │
      |│ ca          │ compile all       │
      |│ t           │ test              │
      |│ r           │ run               │
      |│ styleCheck  │ fmt check         │
      |│ styleFix    │ fmt               │
      |│ up2date     │ dependencyUpdates │
      |╰─────────────┴───────────────────╯""".stripMargin
