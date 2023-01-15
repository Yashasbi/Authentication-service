#!/bin/bash

PID =`ps -ef | grep AuthorizationService-1.0.jar | grep -v grep | awk '{print $2}'`

if [ -n "${PID}" ]
then
  kill -9 $PID
fi