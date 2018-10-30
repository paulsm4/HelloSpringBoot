#!/bin/sh
curl \
  -d '{"id":4, "summary":"New custom Todo"}' \
  -H "Content-Type: application/json" \
  -X POST http://localhost:8080/newTodo
