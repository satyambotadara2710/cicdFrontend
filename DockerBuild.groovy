def buildDockerImage(String reponame,String imagename,String version){
    bat "docker build -t $reponame/$imagename:$version -t $reponame/$imagename:latest ."
}
return this;