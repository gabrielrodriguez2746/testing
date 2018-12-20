# Test base image relays on Android build env from Uber
FROM uber/android-build-environment:latest

LABEL Description="This base image is used to build and test Test Android app" Vendor="Test Inc" Version="0.1"

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

# Build arguments
ARG MY_USER=1000:1000

# Environment variables
ENV MY_EXEC_USER executor_user
ENV MY_EXEC_GROUP executor_group

# Setup SDK licenses
RUN mkdir -p "${ANDROID_HOME}/licenses"
RUN echo "8933bad161af4178b1185d1a37fbf41ea5269c55" >> "${ANDROID_HOME}/licenses/android-sdk-license"
RUN echo "d56f5187479451eabf01fb78af6dfcb131a6481e" >> "${ANDROID_HOME}/licenses/android-sdk-license"
RUN echo "84831b9409646a918e30573bab4c9c91346d8abd" >> "${ANDROID_HOME}/licenses/android-sdk-preview-license"

# Fix permissions
USER root
RUN groupadd -o -g $(echo $MY_USER | cut -d':' -f 2) $MY_EXEC_GROUP && \
    useradd -o --create-home -u $(echo $MY_USER | cut -d':' -f 1) -g $MY_EXEC_GROUP $MY_EXEC_USER
RUN chown -R $MY_USER $ANDROID_HOME $ANDROID_SDK_HOME $ANDROID_NDK_HOME
RUN chmod -R a+rx $ANDROID_HOME $ANDROID_SDK_HOME $ANDROID_NDK_HOME
USER $MY_EXEC_USER
