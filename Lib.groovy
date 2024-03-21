def pushImageToRepo(String userName,String password,String imagename,String dockerRepo){
    // login to docker hub
    bat "docker login -u "+userName+" -p "+password
    bat "docker push -a "+dockerRepo+"/"+imagename
}

def buildDockerImage(String reponame,String imagename,String version){
    bat "docker build -t "+ reponame+"/"+imagename+":"+version+ " -t " +reponame+"/"+imagename+":latest ."
}

def runContainer(String reponame,String imagename,String containername,int port){
    bat "docker pull "+reponame+"/"+imagename
    bat ".\\runcontainer.bat "+port+" "+containername+" "+reponame+"/"+imagename
}

def hello(){
    echo "hello world"
}

return this;
