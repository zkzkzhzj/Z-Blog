import { useEffect, useState } from 'react'

function App() {
  const [data, setData] = useState<string>('서버 연결...')

  useEffect(() => {
    // 스프링 api 호출
    fetch('/api/hello')
      .then((res) => res.text()) // axios 로 변경
      .then((text) => setData(text))
      .catch(() => setData('서버 연결 실패'))
  }, [])

  return (
    <div style={{ padding: '20px' }}>
      <h1>Z-Blog</h1>
      <p>백엔드 연결 테스트: <strong>{data}</strong></p>
    </div>
  )
}

export default App