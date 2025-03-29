
/**
 *  사용자가 원하는 이미지 다운로드 받기
 *  S3 이미지 접근
 */
function downloadImg() {
  // 데이터 속성에서 이미지 URL 가져오기
  const imageContainer = document.getElementById("imageContainer");
  const imageUrls = imageContainer.dataset.urls.replace(/\[|\]/g, "").split(', ').map(url => url.trim());

  // 각 이미지 다운로드 실행
  imageUrls.forEach((url, index) => {
    setTimeout(() => {
      const link = document.createElement("a");
      link.href = url;
      link.download = url.split('/').pop() // 파일명 추출
      document.body.appendChild(link);
      link.click()
      document.body.removeChild(link);
    }, index * 100); // 0.5초 간격으로 다운로드 실행
  });
}