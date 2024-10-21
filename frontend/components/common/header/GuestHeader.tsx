"use client";

import Image from "next/image";
import { useState } from "react";

const GuestHeader = () => {
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
          <nav className="relative">
            <button
              onClick={toggleDropdown}
              className="p-2 text-black font-bold bg-white bg-opacity-20 rounded-md hover:bg-opacity-30 transition-all cursor-pointer">
              Contact US
            </button>
            {isDropdownOpen && (
              <ul className="absolute right-0 mt-2 w-48 bg-white text-black rounded-md shadow-lg">
                <li className="p-2 hover:bg-gray-100 cursor-pointer">태욱</li>
                <li className="p-2 hover:bg-gray-100 cursor-pointer">현진</li>
                <li className="p-2 hover:bg-gray-100 cursor-pointer">연락해!</li>
              </ul>
            )}
          </nav>

          {/** 이건 로그인 회원가입 버튼들 */}
          <div className="flex gap-3 mr-3">
            <button className="btn-common btn-hover bg-transparent font-poppins text-black font-bold px-2 py-1 lg:px-4 lg:py-2 rounded-lg border-2 border-green-500 shadow-md hover:bg-green-500 hover:text-black transition-colors duration-300">
              Join
            </button>
            <button className="btn-common btn-hover bg-transparent font-poppins text-black font-bold px-2 py-1 lg:px-4 lg:py-2 rounded-lg border-2 border-green-500 shadow-md hover:bg-green-500 hover:text-black transition-colors duration-300">
              SignUp
            </button>
          </div>
        </div>
      </header>
    </>
  );
};

export default GuestHeader;
