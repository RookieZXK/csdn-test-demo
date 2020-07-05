#!/bin/bash
ps -ef | grep csdn-test-demo | grep -v grep | awk '{print $2}' | xargs kill -9
