#!/bin/bash
base_dir=$HOME/cassandra4logkv/apache-cassandra-1.0.6/conf
config_file=cassandra.yaml
cp $base_dir/$config_file ./

count=0
for line in `cat ../workers`;do
   let count=count+1
done
echo $count

i=0
for line in `cat ../workers`;do
   echo $i
   python tokengentool $count $i
   let i=i+1
   scp $config_file $line:$base_dir
   echo copied to $line:$base_dir
done;
