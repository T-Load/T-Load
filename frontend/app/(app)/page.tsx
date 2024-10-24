"use client";

import MainForm from "@components/common/MainForm";
import { useEffect } from "react";

export default function MainPage() {
  useEffect(() => {
    const fetchData = async () => {
      const url = "https://fd82-1-237-90-238.ngrok-free.app/api/users/profile/1";

      // 헤더 설정
      const headers = {
        "Content-Type": "application/json",
        "ngrok-skip-browser-warning": "69420",
      };

      // 요청 옵션 설정
      const options = {
        method: "GET",
        headers: headers,
      };

      try {
        const response = await fetch(url, options);

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log(data);
      } catch (error) {
        console.error("Fetch error:", error);
      }
    };

    fetchData();
  }, []);

  return (
    <>
      <MainForm />
    </>
  );
}
