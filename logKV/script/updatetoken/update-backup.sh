#!/bin/bash
base_dir=/home/caoz/cassandra4logkv/apache-cassandra-1.0.6/conf
config_file=cassandra.yaml
cp $base_dir/$config_file ./
count=`wc -l /home/caoz/logkvjar/hosts | grep -o '[0-9]'`
echo $count
i=0
for line in `cat /home/caoz/logkvjar/hosts`;do
echo $i
python tokengentool $count $i
let i=i+1
scp $config_file $line:$base_dir
echo copied to $line:$base_dir
done;
