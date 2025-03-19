const baseUrl = 'http://localhost:8080'

async function getPost(postId) {
    try {
        const response = await fetch(baseUrl+'/posts/'+postId);
        const body = await response.json();
        return {response, body: body}
    } catch (error) {
        console.error('error', error);
    }
}

async function getTotalSize(keyword, target){
    try{
        const response = await fetch(baseUrl+'/posts/total-size?keyword='+keyword+'&target='+target)
        const body = await response.json();
        return {status: response.status, body: body}
    } catch(error){
        console.error('error', error);
    }
}

async function getPostList(page, size, sort, order, keyword, target){
    try {
            
        const response = await fetch(baseUrl+'/posts?page='+ page + '&size='+size+'&sort='+sort+','+order+'&keyword='+keyword+'&target='+target);
        const body = await response.json();
        return {status: response.status, body:body}
    } catch (error) {
        console.error('error', error);
    }
}

async function deletePost(postId,data){

    const url = baseUrl+`/posts/${postId}`;
    try{
        const response = await fetch(url, {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
                // "Authorization":jwt
            },
            body: JSON.stringify(data),
            credentials:"include"
        })
        const body = await response.json();
        return {response: response, body:body}
    }   
    catch(error){
        console.error('error', error);
    }
}

async function postPost(postData,jwt){
    console.log(jwt)
    try{
        const response = await fetch("http://localhost:8080/posts", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                // "Authorization":jwt
            },
            body: JSON.stringify(postData),            
            credentials:"include"
        })
        const body = await response.json();
        return {status:response.status, body:body}

    }catch(error){
        console.error('error', error);
    }
}

async function putPost(putData, jwt){

    try{
        const response = await fetch(`http://localhost:8080/posts/${putData.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                // 'Authorization':jwt
            },
            body: JSON.stringify(putData),
            credentials:"include"
        })
        const body = await response.json();

        return {status:response.status, body:body};
    }catch(error){
        console.error('error', error);
    }
}

async function login(data){
    try{
        const response = await fetch(baseUrl+'/users/login',{
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            credentials:"include",
            body:JSON.stringify(data)  
        })
        const body = await response.json();
        return {response:response, body:body};
    } catch(error){
        console.error('error', error);
    }
}

async function signup(data){
    try{
        const response = await fetch(baseUrl+'/users/signup',{
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body:JSON.stringify(data)
        })
        const body = await response.json();
        console.log(response.headers.get('Authorization'))
        return {response:response, body:body};
    } catch(error){
        console.error('error', error);
    }
}

async function logout(){
    try{
        const response = await fetch(baseUrl+'/users/logout',{
            method: 'POST',
            credentials:"include"
            
        })
        const body = await response.json();
        return {response:response, body:body}
    } catch(error){
        console.error('error:', error);
    }
}

async function getProfile(userId){
    try{
        const response = await fetch(baseUrl + '/users/'+userId,{
            method:'GET',
            credentials:"include",
            headers:{
                // "Authorization": localStorage.getItem('jwt')
            }
        })
        const body = await response.json();
        return {response:response, body:body}
    } catch(error){
        console.error('error',error)
    }
}

async function reissue(){
    try{
        const response = await fetch(baseUrl + '/users/reissue',{
            method:"POST",
            credentials:"include"
        })
        const body = await response.json();
        localStorage.setItem('jwt',response.headers.get('Authorization'))
        return {response:response, body:body}
    }catch(error){
        console.error('error',error)
    }
}

async function postComment(data){
    console.log(JSON.stringify(data))
    try{
        const response = await fetch(baseUrl + `/posts/${data.postId}/comments`,{
            method:"POST",
            credentials:"include",
            headers:{
                "Content-Type":"application/json",
                // "Authorization":localStorage.getItem('jwt')
            },
            body: JSON.stringify(data)
        })        
        const body = await response.json()
        return {response:response, body:body};
    }catch(error){
        console.error('error',error)
    }
}

async function deleteComment(commentId){
    try{
        const response = await fetch(baseUrl + `/comments/${commentId}`,{
            method:"DELETE",
            credentials:"include",
            headers:{
                // "Authorization":localStorage.getItem('jwt')
            }
        })        
        const body = await response.json()
        return {response:response, body:body};
    }catch(error){
        console.error('error',error)
    }
}

async function postPostLike(postId){
    try{
        const response = await fetch(baseUrl + `/posts/${postId}/likes`,{
            method:"POST",
            credentials:"include",
            headers:{
                // "Authorization":localStorage.getItem('jwt')
            }
        })        
        const body = await response.json()
        return {response:response, body:body};
    }catch(error){
        console.error('error',error)
    }
}

async function deletePostLike(postId){
    try{
        const response = await fetch(baseUrl + `/posts/${postId}/likes`,{
            method:"DELETE",
            credentials:"include",
            headers:{
                // "Authorization":localStorage.getItem('jwt')
            }
        })        
        const body = await response.json()
        return {response:response, body:body};
    }catch(error){
        console.error('error',error)
    }
}

async function updateUserNickname(userId,data){
    try{
        const response = await fetch(baseUrl + `/users/${userId}`,{
            method:"PUT",
            credentials:"include",
            headers:{
                "Content-Type":"application/json",
                // "Authorization":localStorage.getItem('jwt')
            },
            body: JSON.stringify(data)
        })        
        const body = await response.json()
        return {response:response, body:body};
    }catch(error){
        console.error('error',error)
    }
}

async function deleteUser(userId, data){
    console.log(data)
    try{
        const response = await fetch(baseUrl + `/users/${userId}`,{
            method:"DELETE",
            credentials:"include",
            headers:{
                "Content-Type":"application/json",
                // "Authorization":localStorage.getItem('jwt')
            },
            body: JSON.stringify(data)
        })        
        const body = await response.json()
        return {response:response, body:body};
    }catch(error){
        console.error('error',error)
    }
}

async function uploadImage(file) {
    const formData = new FormData()
    formData.append('file', file)
    console.log(file)
    try{
        const response = await fetch(baseUrl + `/images`,{
            method:"POST",
            credentials:"include",
            headers:{
                // "Content-Type":"multipart/form-data",
                // "Authorization":localStorage.getItem('jwt')
            },
            body: formData
        })        
        const body = await response.json()
        return {response:response, body:body};
    }catch(error){
        console.error('error',error)
    }
}

export {getPost, getTotalSize, getPostList, deletePost, 
    postPost, putPost, login, logout, getProfile, reissue, 
    postComment, deleteComment, postPostLike, deletePostLike,
    updateUserNickname, deleteUser,
    uploadImage
};