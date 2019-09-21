cmake_minimum_required(VERSION 2.8.9)
project (hello)
find_package(Threads)
add_executable(helloworld helloworld.cpp)
add_executable(usespthread usespthread.cpp)
target_link_libraries (usespthread ${CMAKE_THREAD_LIBS_INIT})

set(CMAKE_BUILD_TYPE Release)
include_directories(myengine)
file(GLOB SOURCES "myengine/*.cpp")

add_library(MyEngineDynamic SHARED ${SOURCES})
add_library(MyEngineStatic STATIC ${SOURCES})


install(TARGETS MyEngineStatic DESTINATION /usr/local/lib)
install(FILES myengine/myengine.hpp DESTINATION /usr/local/include)

install(TARGETS MyEngineDynamic DESTINATION /usr/local/lib)
