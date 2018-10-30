#!/bin/sh
if [ $# -eq 0 ]; then
  curl http://localhost:8080/getTodos
else 
  curl http://localhost:8080/getTodos/?limit=$1
fi
