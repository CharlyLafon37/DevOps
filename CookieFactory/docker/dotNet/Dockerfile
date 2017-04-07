FROM mono:3.10

COPY ./server.exe ./server.exe

EXPOSE 9090

ENTRYPOINT ["mono", "./server.exe"]

CMD ["/standalone"]

