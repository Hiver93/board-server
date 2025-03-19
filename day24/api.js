const baseUrl = 'http://localhost:8080'

async function getPost(postId) {
    try {
        const response = await fetch(baseUrl+'/posts/'+postId);
        const body = await response.json();
        return {status: response.status, body: body}
    } catch (error) {
        console.error('데이터를 가져오는 데 오류가 발생했습니다:', error);
    }
}

async function getTotalSize(keyword, target){
    try{
        const response = await fetch(baseUrl+'/posts/total-size?keyword='+keyword+'&target='+target)
        const body = await response.json();
        return {status: response.status, body: body}
    } catch(error){
        console.error('데이터를 가져오는 데 오류가 발생했습니다:', error);
    }
}

async function getPostList(page, size, sort, order, keyword, target){
    try {
            
        const response = await fetch(baseUrl+'/posts?page='+ page + '&size='+size+'&sort='+sort+','+order+'&keyword='+keyword+'&target='+target); // 서버 URL (게시글 목록 API)
        const body = await response.json();
        return {status: response.status, body:body}
    } catch (error) {
        console.error('데이터를 가져오는 데 오류가 발생했습니다:', error);
    }
}

async function deletePost(postId, password){

    const data = {
        "password": password
    }

    const url = baseUrl+`/posts/${postId}`;
    try{
        const response = await fetch(url, {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json"
            },
            body:JSON.stringify(data)
        })
        const body = await response.json();
        return {status: response.status, body:body}
    }   
    catch(error){
        console.log(error)
    }
}


export {getPost, getTotalSize, getPostList, deletePost};