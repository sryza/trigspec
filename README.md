Trigger Speculative Execution
=============================
This repository contains a MapReduce program that triggers speculative
execution.

## Building

    mvn install

## Generating the input files

    hadoop jar target/trigspec-1.0-SNAPSHOT.jar TrigSpecGen outdir 10

## Running

    hadoop jar target/trigspec-1.0-SNAPSHOT.jar TrigSpecDriver indir
