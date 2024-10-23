"use client";

import Button from "@components/button/Button";
import { Button as NextUIButton } from "@nextui-org/react";
import { useEffect, useState } from "react";

export default function CustomButton() {
  const [userData, setUserData] = useState(null);

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
    <div className="flex justify-center items-center h-screen">
      {/* NextUI Button with Tailwind CSS */}
      <NextUIButton className="bg-blue-400 text-white text-sm md:text-lg p-4 rounded-md hover:bg-purple-400">
        Tailwind + NextUI Button
      </NextUIButton>
      <Button>dd</Button>
    </div>
  );
}
