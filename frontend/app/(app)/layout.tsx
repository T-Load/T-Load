import GNB from "@components/common/GNB";
import { ReactNode } from "react";

//오직 PC 버전만 지원
export default function EditLayout({ children }: { children: ReactNode }) {
  return (
    <div className="flex flex-col min-h-screen">
      <GNB haveCookie={true} />
      <div className="flex flex-1">
        {/* <div className="w-[70px] bg-green-300">난 사이드바!</div> */}
        <div className="flex-1">{children}</div>
      </div>
      <div>난 푸터입니다</div>
    </div>
  );
}
