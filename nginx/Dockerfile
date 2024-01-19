# auto renew ssl certificate
# FROM ohttps/ohttps-nginx
# WORKDIR /etc/nginx
# COPY default.conf /etc/nginx/conf.d/default.conf
# ENV PUSH_NODE_ID='push-4x6dk0nvevr1n2zm'
# ENV PUSH_NODE_TOKEN='3ad5266df6ba4c15e91e50bc212bfd1e'

# http
FROM nginx
COPY /nginx/default.conf /etc/nginx/conf.d/default.conf
RUN nginx -t
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]