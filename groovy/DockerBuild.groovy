def buildDockerImage(string reponame,string imagename,string version){
    bat "docker build -t $reponame/$imagename:$version -t $reponame/$imagename:latest ."
}
return this;