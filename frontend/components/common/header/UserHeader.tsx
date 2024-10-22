"use client";

import Image from "next/image";
import { useState } from "react";

const UserHeader = () => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);

  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  return (
    <>
      <header className="flex h-[70px] bg-white items-center justify-around shadow-md p-4 border-t-4 border-green-700">
        {/** 이건 로고 */}
        <div className="flex items-center gap-6">
          <div className="relative w-14 h-14 overflow-hidden rounded-full border-2 border-white shadow-lg">
            <Image src="/image/Main_Logo.webp" layout="fill" objectFit="cover" alt="로고" />
          </div>
          <span className="text-black font-bold text-4xl drop-shadow-lg font-roboto">T-Load</span>
        </div>

        <div className="flex gap-6 lg:gap-24">
          {/** 이건 navs */}
          {/* 랜딩 사이트를 2개로 분할하여 만들어, 로그인된 사이트에서는 검색이 가능하도록하여 해당 검색어를 필터링하여 볼 수 있도록 설정 */}
          <nav className="relative gap-12">
            <button
              onClick={toggleDropdown}
              className="p-2 text-black font-bold bg-white bg-opacity-20 rounded-md hover:bg-opacity-30 transition-all cursor-pointer">
              Go Trip →
            </button>
            {/* 내부에서 게시물 조회, 수정, 삭제가 가능 */}
            <button
              onClick={toggleDropdown}
              className="p-2 text-black font-bold bg-white bg-opacity-20 rounded-md hover:bg-opacity-30 transition-all cursor-pointer">
              게시물
            </button>
            {/* 내부에서 후기 열람 및 삭제 가능*/}
            <button
              onClick={toggleDropdown}
              className="p-2 text-black font-bold bg-white bg-opacity-20 rounded-md hover:bg-opacity-30 transition-all cursor-pointer">
              후기
            </button>
          </nav>

          {/** 이건 로그인 회원가입 버튼들 */}
          <div className="flex gap-3 mr-3">
            <button className="btn-common btn-hover bg-transparent font-poppins text-black font-bold px-2 py-1 lg:px-4 lg:py-2 rounded-lg border-2 border-green-500 shadow-md hover:bg-green-500 hover:text-black transition-colors duration-300">
              LogOut
            </button>
          </div>
        </div>
      </header>
    </>
  );
};

export default UserHeader;
