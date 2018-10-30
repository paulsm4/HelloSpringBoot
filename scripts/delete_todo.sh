#!/bin/sh
if [ $# -eq 0 ]; then
  echo USAGE: $0 '<id>'
  exit
fi
curl -X DELETE http://localhost:8080/deleteTodo/$1
