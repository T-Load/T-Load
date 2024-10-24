"use client";

import Image from "next/image";
import { useEffect, useState } from "react";

const MainInfo = () => {
  const [isTextVisible, setIsTextVisible] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setIsTextVisible(true);
    }, 1000);
    return () => clearTimeout(timer);
  }, []);

  return (
    <div className="flex-center w-full h-[500px] relative">
      <Image
        src={"/image/배경사진.jpg"}
        width={500}
        height={300}
        alt={"메인페이지"}
        className={`object-cover w-full h-full blur-sm transition-filter duration-750 ${
          isTextVisible ? "blur-sm" : "blur-none"
        }`}
      />
      <div
        className={`flex flex-col absolute left-1/2 top-1/2 transform -translate-x-1/2 -translate-y-1/2 z-10 text-center text-white gap-8 transition-opacity duration-1000 ${
          isTextVisible ? "opacity-100 blur-none" : "opacity-0 blur-sm"
        }`}>
        <p
          className="font-poppins text-green-700 text-[50px] font-semibold"
          style={{ textShadow: "0 0 3px white, 0 0 10px white" }}>
          세상 어디든
        </p>
        <p className="font-poppins text-[60px] font-bold">Trip with T-Load</p>
      </div>
    </div>
  );
};

export default MainInfo;
